package com.jusdt.es.common.action;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.base.Joiner;
import com.jusdt.es.common.params.Parameters;

@SuppressWarnings("unchecked")
public abstract class AbstractMultiIndexActionBuilder<T extends Action<?>, K> extends AbstractAction.Builder<T, K> {
	protected Set<String> indexNames = new LinkedHashSet<String>();

	public K addIndex(String indexName) {
		this.indexNames.add(indexName);
		return (K) this;
	}

	public K addIndices(Collection<? extends String> indexNames) {
		this.indexNames.addAll(indexNames);
		return (K) this;
	}

	/**
	 * Ignore unavailable indices, this includes indices that not exists or closed indices.
	 * @param ignore whether to ignore unavailable indices
	 */
	public K ignoreUnavailable(boolean ignore) {
		setParameter(Parameters.IGNORE_UNAVAILABLE, String.valueOf(ignore));
		return (K) this;
	}

	/**
	 * Fail of wildcard indices expressions results into no concrete indices.
	 * @param allow whether to allow no indices.
	 */
	public K allowNoIndices(boolean allow) {
		setParameter(Parameters.ALLOW_NO_INDICES, String.valueOf(allow));
		return (K) this;
	}

	public String getJoinedIndices() {
		if (indexNames.size() > 0) {
			return Joiner.on(',').join(indexNames);
		} else {
			return "_all";
		}
	}

	@Override
	abstract public T build();

}
