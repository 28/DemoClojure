(ns system.music
  (:import (java.io File)
           (javax.sound.sampled Clip AudioFormat AudioInputStream AudioSystem DataLine DataLine$Info AudioFileFormat AudioFileFormat$Type)))

(def file (File. "src/Test.wav"))
(def inputstream (AudioSystem/getAudioInputStream file))
(def formata (. inputstream getFormat))
(def info (DataLine$Info. Clip formata))
(def clip (AudioSystem/getLine info))
(. clip open inputstream)
(. clip setFramePosition (/ (. clip getFrameLength) 2) )
(def fileo (File. "src/Test1.wav"))
(if 
  (AudioSystem/isFileTypeSupported AudioFileFormat$Type/WAVE inputstream)
  (AudioSystem/write inputstream AudioFileFormat$Type/WAVE fileo)
  false)