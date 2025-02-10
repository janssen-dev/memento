import { defineStore } from "pinia";
import { fetchNotes, createNote, updateNotes, deleteNote } from "@/api/note-api";
import type Note from "@/types/Note";

export const useNoteStore = defineStore("note", {
  state: () => ({
    notes: [] as Note[],
  }),

  actions: {
    async loadNotes() {
      try {
        this.notes = await fetchNotes();
      } catch (error) {
        console.error("Failed to load notes", error);
        throw error;
      }
    },

    async addNote(note: Note) {
      try {
        const newNote = await createNote(note);
        this.notes.push(newNote!);
      } catch (error) {
        console.error("Failed to add note", error);
        throw error;
      }
    },

    async updateNotes(notes: Note[]) {
      try {
        await updateNotes(notes);
        this.notes = this.notes.map(note => {
          const updatedNote = notes.find(n => n.id === note.id);
          return updatedNote ? updatedNote : note;
        });
      } catch (error) {
        console.error("Failed to update notes", error);
        throw error;
      }
    },

    async deleteNote(noteId: string) {
      try {
        await deleteNote(noteId);
        this.notes = this.notes.filter(note => note.id !== noteId);
      } catch (error) {
        console.error("Failed to delete note", error);
        throw error;
      }
    },
  },
});
