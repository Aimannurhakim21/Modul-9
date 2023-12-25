public class Group {
    public List getUsersSortedByMostRecentlyRegistered() {
        List<User> users = new ArrayList<>();

        if (!userDirectoryExists()) {
            return users;
        }

        addFoundUsersTo(users);
        sortByMostRecentlyRegistered(users);

        return users;
    }

    private boolean userDirectoryExists() {
        String persistencePath = getPersistencePath();
        File persistenceDirectory = new File(persistencePath);
        return persistenceDirectory.exists();
    }

    private void addFoundUsersTo(List<User> users) {
        File[] files = new File(getPersistencePath()).listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                users.add(new User(file.getName(), this));
            }
        }
    }

    private void sortByMostRecentlyRegistered(List<User> users) {
        Collections.sort(users, new User.UserComparatorByDescendingRegistration());
    }
}

