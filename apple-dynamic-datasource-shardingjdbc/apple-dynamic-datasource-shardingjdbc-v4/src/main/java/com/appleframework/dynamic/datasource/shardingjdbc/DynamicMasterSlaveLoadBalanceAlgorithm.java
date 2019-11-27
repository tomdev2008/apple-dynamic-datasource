package com.appleframework.dynamic.datasource.shardingjdbc;

import org.apache.commons.lang3.RandomUtils;
import org.apache.shardingsphere.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appleframework.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

public class DynamicMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {

	private static final Logger logger = LoggerFactory.getLogger(DynamicMasterSlaveLoadBalanceAlgorithm.class);

	private Properties properties = new Properties();

	@Override
	public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
		String key = DynamicDataSourceContextHolder.peek();
		if (null == key) {
			if (slaveDataSourceNames.size() == 0) {
				key = masterDataSourceName;
			} else if (slaveDataSourceNames.size() == 1) {
				key = slaveDataSourceNames.get(0);
			} else {
				int random = RandomUtils.nextInt(0, slaveDataSourceNames.size());
				key = slaveDataSourceNames.get(random);
			}
		} else {
			if (key.equals(masterDataSourceName) || slaveDataSourceNames.contains(key)) {
				return key;
			} else {
				if (logger.isWarnEnabled()) {
					logger.warn("the datasource " + key + " is not exist!!! used the " + masterDataSourceName + " datasouce!");
				}
				key = masterDataSourceName;
			}
		}
		return key;
	}

	@Override
	public String getType() {
		return "dynamicLoadBalance";
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public void setProperties(Properties properties) {
		if (null != properties) {
			properties.putAll(properties);
		}
	}

}