<script lang="ts">
	import type { Snippet } from 'svelte';

	interface Props {
		children: Snippet;
		onclick: () => void;
	}

	let { onclick, children }: Props = $props();
</script>

<button class="btn" {onclick}>
	{@render children()}
</button>

<style>
</style>
