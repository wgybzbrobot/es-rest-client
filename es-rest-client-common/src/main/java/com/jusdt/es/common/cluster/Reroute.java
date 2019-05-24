package com.jusdt.es.common.cluster;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.cluster.reroute.RerouteCommand;

public class Reroute extends GenericResultAbstractAction {

	protected Reroute(Builder builder) {
		super(builder);

		List<Map<?, ?>> actions = new LinkedList<>();
		for (RerouteCommand rerouteCommand : builder.commandList) {
			String type = rerouteCommand.getType();
			Map<String, Object> data = rerouteCommand.getData();
			actions.add(ImmutableMap.of(type, data));
		}
		this.payload = ImmutableMap.<String, Object> of("commands", actions);
	}

	@Override
	protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
		return super.buildURI(elasticsearchVersion) + "/_cluster/reroute";
	}

	@Override
	public String getRestMethodName() {
		return "POST";
	}

	public static class Builder extends AbstractAction.Builder<Reroute, Builder> {

		private List<RerouteCommand> commandList = new LinkedList<>();

		public Builder(RerouteCommand rerouteCommand) {
			commandList.add(rerouteCommand);
		}

		public Builder(Collection<RerouteCommand> rerouteCommands) {
			commandList.addAll(rerouteCommands);
		}

		public Builder addCommand(RerouteCommand rerouteCommand) {
			commandList.add(rerouteCommand);
			return this;
		}

		public Builder addCommands(Collection<RerouteCommand> rerouteCommands) {
			commandList.addAll(rerouteCommands);
			return this;
		}

		@Override
		public Reroute build() {
			return new Reroute(this);
		}

	}

}
