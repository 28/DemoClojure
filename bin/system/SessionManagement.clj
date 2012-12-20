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
  (if (empty? @session) 
    false
    (if (contains? @session (keyword username))
      (do
        (swap! session dissoc (keyword username))
        true)
      false)))

;;End of file system.sessionmanagement