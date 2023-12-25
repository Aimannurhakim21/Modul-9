public class Group {
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String persistencePath = persistencePath();
        File persistenceDirectory = new File(persistencePath);

        if (!persistenceDirectory.exists()) {
            return users;
        }

        File[] files = persistenceDirectory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                users.add(new User(file.getName(), this));
            }
        }

        Collections.sort(users, new User.UserComparatorByDescendingRegistration());

        return users;
    }
}

