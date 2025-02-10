import api from "./axios-api";
import type Note from "@/types/Note";

export const updateNotes = async (notes: Note[]) => {
  try {
    await api.patch('/notes', notes);
  } catch (error) {
    console.error('Failed to update notes', error);
  }
};

export const fetchNotes = async () => {
  try {
    const response = await api.get('/notes');
    return response.data as Note[];
  } catch (error) {
    console.error('Failed to fetch notes', error);
    throw error;
  }
};

export const createNote = async (note: Note) => {
  try {
    const response = await api.post('/notes', note);
    return response.data as Note;
  } catch (error) {
    console.error('Failed to create note', error);
  }
};

export const deleteNote = async (noteId: string) => {
  try {
    await api.delete(`/notes/${noteId}`);
  } catch (error) {
    console.error('Failed to delete note', error);
  }
};
