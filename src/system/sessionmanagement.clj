;;This namespace contains controls the (log in) session.
(ns system.sessionmanagement)

(def session (atom {}))

(defn add-to-session
  "Adds username to the sesison and gives it a session id."
  [username]
  (swap! session assoc (keyword username) (inc (count @session))))

(defn remove-from-session
  "Removes the username from session."
  [username]
  (reset! session {}))
