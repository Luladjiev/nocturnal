import type { PageLoad } from './$types';

export const load: PageLoad = ({ fetch }) => {
	return {
		projects: fetch('/api/projects').then(r => r.json())
	};
};