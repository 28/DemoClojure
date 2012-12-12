(ns system.SessionManagement)

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

(defn logged?
  ""
  [username]
  (if (contains? @session (keyword username))
    true
    false))