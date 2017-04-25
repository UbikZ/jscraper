import 'whatwg-fetch';

export function getFeedItems() {
  return fetch('/api/feed-item?start-date=2017-04-15')
      .then(response => response.json())
      .then(json => {
        console.log('parsed json', json);
        if (json.success) {
          return Promise.resolve(json.items);
        }
        throw new Error(json.data);
      })
      .catch(error => {
        console.error('Error', error);
        Promise.reject(error);
      });
}
