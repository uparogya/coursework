public class Melody {
    private Queue <Note> queue = new ListQueue <Note>();
    private Note note = null;

    //Pre-requisites for repeat section
    private boolean repeatFlag = false;
    private Queue <Note> repeatSection = new ListQueue <Note>();
    private int repeatSize = 0;

    public Melody(Queue <Note> song) { this.queue = song; }

    public double getTotalDuration() {
        int queueSize = getSize();
        Queue <Note> temporaryQueue = temporaryQueue();
        double duration = 0.0;

        for (int i = 0; i < queueSize; i++) {
            Note tempNote = temporaryQueue.remove();
            duration += tempNote.getDuration();

            if (tempNote.isRepeat()) repeatOnOff();
            if (repeatFlag) {
                duration += tempNote.getDuration();
            } else if (tempNote.isRepeat() && !repeatFlag) {
                duration += tempNote.getDuration();
            }
        }

        return duration;
    }

    public void changeTempo(double tempo) {
        int queueSize = getSize();
        Queue <Note> temporaryQueue = temporaryQueue();

        for (int i = 0; i < queueSize; i++) {
            Note tempNote = temporaryQueue.remove();
            note = new Note(
                    tempNote.getDuration()*tempo,
                    tempNote.getPitch(),
                    tempNote.getOctave(),
                    tempNote.getAccidental(),
                    tempNote.isRepeat()
            );
            queue.remove();
            queue.add(note);
        }
    }

    public void reverse() {
        int queueSize = getSize();
        Queue <Note> temporaryQueue = temporaryQueue();
        Stack <Note> tempStack = new ArrayStack <Note>();

        for (int i = 0; i < queueSize; i++) {
            tempStack.push(temporaryQueue.remove());
        }
        this.queue = new ListQueue <Note>(); //Resetting the queue
        for (int i = 0; i < queueSize; i++) {
            queue.add(tempStack.pop());
        }

    }

    public void append(Melody other) {
        int newlyLoadedSongSize = other.getSize();
        Queue <Note> newlyLoadedSongTemp = other.temporaryQueue();
        for (int i = 0; i < newlyLoadedSongSize; i++) {
            queue.add(newlyLoadedSongTemp.remove());
        }
    }

    public void play() {
        int queueSize = getSize();
        Queue <Note> temporaryQueue = temporaryQueue();

        for (int i = 0; i < queueSize; i++) {
            Note tempNote = temporaryQueue.remove();

            StdAudio.play(
                    tempNote.getDuration(),
                    tempNote.getPitch(),
                    tempNote.getOctave(),
                    tempNote.getAccidental()
            );

            if (tempNote.isRepeat()) repeatOnOff();
            if (repeatFlag) {
                repeatSection.add(tempNote);
                repeatSize++;
            } else if (tempNote.isRepeat() && !repeatFlag) { //For the last repeat note
                repeatSection.add(tempNote);
                repeatSize++;
                playRepeatSection();
            }
        }
    }

    public String toString() {
        int queueSize = getSize();
        Queue<Note> temporaryQueue = temporaryQueue();

        String outputString = "\n---------------\n";
        for (int i = 0; i < queueSize; i++) {
            outputString += temporaryQueue.remove() + "\n";
        }
        outputString += "---------------\n\n";

        return outputString;
    }

    private int getSize() { return queue.size(); } //Returns the size of queue

    private Queue <Note> temporaryQueue() { //Creates a temporary queue
        int queueSize = getSize();
        Queue <Note> tempQueue = new ListQueue <Note>();
        Queue <Note> temporaryQueue = new ListQueue <Note>();

        for (int i = 0; i < queueSize; i++) {
            Note currentLine = queue.remove();
            temporaryQueue.add(currentLine);
            tempQueue.add(currentLine);
        }

        queue = tempQueue;
        return temporaryQueue;
    }

    private void repeatOnOff() { //Toggles repeat mode
        if(this.repeatFlag){
            this.repeatFlag = false;
        }else{
            this.repeatFlag = true;
        }
    }
    private void playRepeatSection() { //Plays and resets the repeat buffer
        for (int i = 0; i < repeatSize; i++) {
            Note tempNote = repeatSection.remove();
            StdAudio.play(
                    tempNote.getDuration(),
                    tempNote.getPitch(),
                    tempNote.getOctave(),
                    tempNote.getAccidental()
            );
        }
        repeatSize = 0;
        repeatSection = new ListQueue <Note>();
    }
}
