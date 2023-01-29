class MapTaks{
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

    public Map<String, List<Task>> getTaks() {
        return tasks;
    }
}