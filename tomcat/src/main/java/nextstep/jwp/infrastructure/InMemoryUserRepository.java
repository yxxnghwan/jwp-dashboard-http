package nextstep.jwp.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import nextstep.jwp.domain.model.User;
import nextstep.jwp.domain.model.UserRepository;

public class InMemoryUserRepository implements UserRepository {

    private static final Map<Long, User> database = new ConcurrentHashMap<>();
    private static final AtomicLong seq = new AtomicLong(1L);

    private static final InMemoryUserRepository instance = new InMemoryUserRepository();

    private InMemoryUserRepository() {
    }

    public static InMemoryUserRepository getInstance() {
        return instance;
    }

    static {
        final Long id = seq.addAndGet(1L);
        final User user = new User(id, "gugu", "password", "hkkang@woowahan.com");
        database.put(id, user);
    }

    @Override
    public User save(final User user) {
        final User newUser = new User(seq.addAndGet(1L), user.getAccount(), user.getPassword(), user.getEmail());
        database.put(newUser.getId(), newUser);
        return newUser;
    }

    @Override
    public Optional<User> findByAccount(final String account) {
        return database.values()
                .stream()
                .filter(user -> user.getAccount().equals(account))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(database.values());
    }
}
