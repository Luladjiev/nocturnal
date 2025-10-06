<script lang="ts">
	import Link from '$lib/components/Link/Link.svelte';
	import type { Snippet } from 'svelte';

	interface Props {
		status: string | number;
		children: Snippet;
	}

	let { status = 404, children }: Props = $props();
</script>

<div>
	<h1>{status}</h1>
	<p>{@render children()}</p>
	<Link href="/">Go Back to Home</Link>
</div>

<style>
	div {
		font-family: Arial, sans-serif;
		text-align: center;
		margin: 0;
		height: 100vh;
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;
	}

	h1 {
		font-size: 5rem;
		margin: 0;
	}

	p {
		font-size: 1.5rem;
		margin: 10px 0 20px;
	}
</style>
