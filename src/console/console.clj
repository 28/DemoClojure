;;This is a console user interface.
(ns console.console
  (:use system.main))

(defn sign-console
  "Displays sign in dialog."
  []
    (println "Enter your first name: ")
    (let
      [f (read-line)
      l (do
           (println "Enter your last name: ")
           (read-line))
      u (do
           (println "Enter your username: ")
           (read-line))
      pf (do
           (println "Enter your password: ")
           (read-line))
      ps (do
           (println "Confirm your password: ")
           (read-line))
      e (do
           (println "Enter your e-mail: ")
           (read-line))]
      (if
        (= pf ps)
          (if
            (sign-up f l u pf e)
            (println "Success!")
            (println "Failure!"))
          (println "Passwords don't match!"))))

(defn log-in-console
  "Displays log in dialog."
  []
    (println "Enter username: ")
    (let
      [u (read-line)
       p (do
           (println "Enter password: ")
           (read-line))]
      (if
        (log-in u p)
        (println (str "Success, you are now logged in as " u "!"))
        (println "Failure!"))))

(defn play-console
  "Displays play sound dialog. User can specify the start second and
   duration to play."
  []
  (if
    (logged? nil)
    (do (println "Enter path to file: ")
    (let
      [file (read-line)
       s (do
           (println "Start second: ")
           (read-string (read-line)))
       e (do
           (println "Seconds to play: ")
           (read-string (read-line)))]
      (if
        (and
          (integer? s)
          (integer? e))
        (play file s e)
        (play file nil nil))))
    (println "You are not logged in!")))

(defn crop-console
  "Displays crop sound dialog. User can specify the crop
   length or just crop the first 30s."
  []
  (if
    (logged? nil)
    (do
    (println "Enter path to file: ")
    (let
      [file (read-line)
       target (do
                (println "Enter target path: ")
                (read-line))
       s (do
           (println "Start second: ")
           (read-string (read-line)))
       a (do
           (println "Amount to cut: ")
           (read-string (read-line)))]
      (if
        (and
          (integer? s)
          (integer? a))
        (cropcomb file target s a)
        (cropcomb file target nil nil))
      (println "Success!")))
    (println "You are not logged in!")))

(defn log-out-console
  "Displays the log out dialog."
  []
    (println "Enter username: ")
    (let [username (read-line)]
    (if
      (logged? username)
      (do
        (log-out username)
        (println "You logged out."))
      (println "You are not logged in!"))))
