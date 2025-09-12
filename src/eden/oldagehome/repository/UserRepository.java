package eden.oldagehome.repository;

import eden.oldagehome.model.User;

public interface UserRepository {
    User findById(String id);
}
