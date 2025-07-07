<script setup lang="ts">
import "gridstack/dist/gridstack.min.css";
import {GridStack} from "gridstack";
import {onMounted, ref} from "vue";
import { useNoteStore } from "@/stores/notes";

const noteStore = useNoteStore();
const grid = ref<GridStack | null>(null);

const createNote = async () => {
  const newNote = {
    id: undefined,
    content: 'New Note',
    x: 0,
    y: 0,
    width: 2,
    height: 2,
    rgbaColor: "#ccccccff"
  };
  await noteStore.addNote(newNote);

  // Add the new note to the grid
  if (grid.value) {
    const lastNote = noteStore.notes[noteStore.notes.length - 1];
    grid.value.addWidget({
      id: lastNote.id,
      content: lastNote.content,
      x: lastNote.x,
      y: lastNote.y,
      w: lastNote.width,
      h: lastNote.height
    });
  }
};

onMounted(async () => {
  await noteStore.loadNotes();

  grid.value = GridStack.init();
  // Map notes to GridStack format (w/h instead of width/height)
  const gridNotes = noteStore.notes.map(note => ({
    id: note.id,
    content: note.content,
    x: note.x,
    y: note.y,
    w: note.width,
    h: note.height
  }));
  grid.value.load(gridNotes);
  grid.value.on("change", (event, items) => {
    const updatedNotes = items.map((item) => {
      return {
        id: item.id!,
        content: item.content!,
        x: item.x!,
        y: item.y!,
        width: item.w!,
        height: item.h!,
        rgbaColor: "#ccccccff"
      };
    });
    noteStore.updateNotes(updatedNotes);
  });
});
</script>

<template>
  <button @click="createNote">Create Note</button>
  <div class="grid-stack"></div>
</template>

<style>
.grid-stack {
  background: #FAFAD2;
  border: 1px solid #ccc;
}

.grid-stack-item-content {
  background-color: #18BC9C;
}
</style>
