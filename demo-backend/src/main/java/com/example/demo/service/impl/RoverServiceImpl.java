package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.RoverConfigDao;
import com.example.demo.domain.LocationInfo;
import com.example.demo.domain.RoverConfig;
import com.example.demo.service.RoverService;
import com.example.demo.service.exception.CommandValidationException;

@Service
public class RoverServiceImpl implements RoverService {
	final String BASE_URL = "http://localhost";

	@Autowired
	RoverConfigDao roverConfigDao;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public String sendCommand(String commandStr) throws CommandValidationException {
		String[] commandLines = commandStr.split("\\n");
		if (commandLines.length % 2 != 1 && commandLines.length > 1)
			throw new CommandValidationException("Not a valid command");

		List<String> commands = new ArrayList<>();

		for (int i = 1; i < commandLines.length; i = i + 2) {
			String command = String.format("%s\n%s\n%s", commandLines[0], commandLines[i], commandLines[i + 1]);
			commands.add(command);
		}

		List<RoverConfig> roverConfigList = roverConfigDao.findByActive(true);
		List<String> commandResult = new ArrayList<>();

		final int itrLimit = roverConfigList.size() < commands.size() ? roverConfigList.size() : commands.size();

		for (int i = 0; i < itrLimit; i++) {
			RoverConfig roverConfig = roverConfigList.get(i);
			String url = String.format("%s:%d/command?cmdStr={commandStr}", BASE_URL, roverConfig.getPort());
			ResponseEntity<String> result = restTemplate.getForEntity(url, String.class, commands.get(i));
			if (result.getStatusCode() == HttpStatus.OK) {
				String body = result.getBody();
				commandResult.add(result.getBody());
				String[] splitedBody = body.split("\\s");
				int coordX = Integer.valueOf(splitedBody[0]);
				int coordY = Integer.valueOf(splitedBody[1]);
				String direction = splitedBody[2];
				roverConfig.setLastLocationInfo(new LocationInfo(coordX, coordY, direction));
				roverConfigDao.save(roverConfig);

			} else {
				throw new CommandValidationException("aaa");
			}

		}

		String result = commandResult.stream().map(x -> x).collect(Collectors.joining("\n"));
		return result;

	}

	@Override
	public List<RoverConfig> findAll() {
		return roverConfigDao.findAll();
	}

}
