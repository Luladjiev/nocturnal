import type { PageLoad } from './$types';

export const load: PageLoad = ({ fetch }) => ({
	projects: fetch('/api/projects').then(r => r.json())
});