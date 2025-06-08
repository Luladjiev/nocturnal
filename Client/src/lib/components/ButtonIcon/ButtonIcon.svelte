<script lang="ts">
	import { type IconProps } from '@lucide/svelte';
	import type { Component } from 'svelte';

	interface Props {
		Icon: Component<IconProps>;
		variant?: 'default' | 'red' | 'green';
		onclick: () => void;
	}

	let { Icon, variant = 'default', onclick }: Props = $props();

	const colors: Record<typeof variant, string> = {
		red: 'preset-tonal-error',
		green: 'preset-tonal-success',
		default: ''
	};
</script>

<button class="btn-icon preset-filled {colors[variant]}" {onclick}>
	<Icon />
</button>
