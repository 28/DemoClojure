;;This namespace contains controls the (log in) session.
(ns system.sessionmanagement)

(def session (atom {}))

(defn add-to-session
  ""
  [username]
  (swap! session assoc (keyword username) (inc (count @session))))

(defn remove-from-session
  ""
  [username]
  (swap! session dissoc (keyword username)))

;;End of file system.sessionmanagement