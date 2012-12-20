;;This is a console GUI namespace.
;;Will be replaced!
(ns system.console
  (:use system.main))

(defn sign-console
  ""
  []
  (do
    (println "Enter your firstname")
    (let
      [f (read-line)
      l (do
           (println "Enter your lastname")
           (read-line))
      u (do
           (println "Enter your username")
           (read-line))
      pf (do
           (println "Enter your password")
           (read-line))
      ps (do
           (println "Confirm your password")
           (read-line))
      e (do
           (println "Enter your email")
           (read-line))]
      (if (= pf ps)
          (if (sign-up f l u pf e)
            (println "Succes!")
            (println "Failure!"))
        (println "Passwords dont match")))))

(defn log-console
  ""
  []
  (do
    (println "Enter username")
    (let
      [u (read-line)
       p (do
           (println "Enter password")
           (read-line))]
      (if (log-in u p)
        (println "Succes!")
        (println "Fail!")))))

(defn play-console
  ""
  []
  (do
    (println "Enter path to file")
    (let 
      [file (read-line)]
      (play file))))

(defn crop-console
  ""
  []
  (do
    (println "Enter path to file")
    (let
      [file (read-line)
       target (do
                (println "Enter target path")
                (read-line))
       s (do
           (println "Start second")
           (read-line))
       a (do
           (println "Amount to cut")
           (read-line))]
      (if
        (and 
          (integer? s)
          (integer? a))
        (cropcomb file target s a)
        (cropcomb file target nil nil)))))

;;End of file system.console