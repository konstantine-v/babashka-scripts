(ns backupwp)

(require '[clojure.tools.cli :refer [parse-opts]]
         '[babashka.fs :as fs])

(def cli-options
  ;; An option with a required argument
  [["-p" "--port PORT" "Port number"
    :default 80
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ["-h" "--help"]
   ["-s" "--save"
    :default "wow"]
   ["-d" "--debug"]
   ["-r" "--rclone"
    :default "gdrive"]
   ["-f" "--files"
    :default "/var/www"]])

(def settings (:options (parse-opts *command-line-args* cli-options)))
(def description "Backup script for cron or systemd to backup your sites")

;; Print help info if passed
(if (true? (get settings :help))
  (let [x cli-options]
    (println description)
    (println "Port Options: -p, --port PORT, Port number - default:80")
    (println (str "Getting help: " (nth x 1)))))

(if (true? (get settings :debug))
  (println settings))

(println "Backup process starting...")

(defn check-program [bin]
  (println ()))

(println "...")

;; (fs/directory? "/usr/bin/rclone")


;; Custom variables
(def custom-vars
  ;; An option with a required argument
  {:file_paths (get settings :files),
   :zip_prefix "My-backup",
   :backup_dir "/tmp/backups/"
   :rclone_drv (get settings :rclone)})

(println (get custom-vars :file_paths))
(println "nice!")
