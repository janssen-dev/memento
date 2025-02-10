<script setup lang="ts">
import "gridstack/dist/gridstack.min.css";
import {GridStack} from "gridstack";
import {onMounted} from "vue";
import {updateNotes} from "@/api/note-api";
import { useNoteStore } from "@/stores/notes";

const noteStore = useNoteStore();

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
};

onMounted(async () => {
  await noteStore.loadNotes();

  const grid = GridStack.init();
  grid.load(noteStore.notes);
  grid.on("change", (event, items) => {
    updateNotes(items.map((item) => {
      return {
        id: item.id!,
        content: item.content!,
        x: item.x!,
        y: item.y!,
        width: item.w!,
        height: item.h!,
        rgbaColor: "#ccccccff"
      };
    }));
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
