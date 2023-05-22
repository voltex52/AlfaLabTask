package task2;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouper {

    public Map<String, Collection<NamedObject>> groupByName(Collection<NamedObject> collection) {
        Map<String, List<NamedObject>> map = collection.stream()
                .collect(Collectors.groupingBy(NamedObject::getName));

        Map<String, Collection<NamedObject>> result = new HashMap<>();

        for (Map.Entry<String, List<NamedObject>> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
