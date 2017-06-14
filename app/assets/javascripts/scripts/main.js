import React from 'react';
import ReactDOM from 'react-dom';

import {
    HashRouter as Router,
    Route,
    Link
} from 'react-router-dom'

import PubDetailComponent from './pub-detail-component.jsx';
import PubListComponent from './pub-list-component.jsx';

const App = () => (
    <main>
        <Route exact path='/' component={PubListComponent}/>
        <Route path='/pub/:id' component={PubDetailComponent}/>
    </main>
);

ReactDOM.render((
    <Router>
        <App />
    </Router>
), document.getElementById('root'));