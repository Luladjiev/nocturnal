<script lang="ts">
	import { type IconProps, LoaderCircle } from '@lucide/svelte';
	import type { Component } from 'svelte';

	interface Props {
		Icon: Component<IconProps>;

		variant?: 'default' | 'red' | 'green';
		disabled?: boolean;
		loading?: boolean;

		onclick: () => void;
	}

	let {
		Icon, variant = 'default',
		disabled = false,
		loading = false, onclick
	}: Props = $props();

	const colors: Record<typeof variant, string> = {
		red: 'preset-tonal-error',
		green: 'preset-tonal-success',
		default: ''
	};
</script>

<button class="btn-icon preset-filled {colors[variant]}" {disabled} onclick={() => !loading && onclick()}>
	{#if loading}
		<LoaderCircle class="animate-spin" />
	{:else}
		<Icon />
	{/if}
</button>
