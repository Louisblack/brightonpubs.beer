import React from 'react';
import ReactDOM from 'react-dom';
import PubListComponent from './pub-list-component.jsx';

if (document.getElementById('reactView')) {
    ReactDOM.render(<PubListComponent />, document.getElementById('reactView'));
}
