package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (accounts.containsKey(account.id())) {
            return false;
        }
        accounts.put(account.id(), account);
        return true;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), getById(account.id()).get(), account);
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id, getById(id).get());
    }

    public synchronized Optional<Account> getById(int id) {
        var result = accounts.get(id);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var from = getById(fromId);
        var to = getById(toId);
        if (from.isPresent() && to.isPresent() && from.get().amount() >= amount) {
            update(new Account(fromId, from.get().amount() - amount));
            update(new Account(toId, to.get().amount() + amount));
            return true;
        }
        return false;
    }
}
