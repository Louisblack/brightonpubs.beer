import React from 'react';
import ReactDOM from 'react-dom';

import {
    HashRouter as Router,
    Route,
    Link
} from 'react-router-dom'

import { browserHistory } from 'react-router';

import PubDetailComponent from './pub-detail-component.jsx';
import PubListComponent from './pub-list-component.jsx';

const App = () => (
    <main>
        <Route exact path='/' component={PubListComponent}/>
        <Route path='/pub/:id' component={PubDetailComponent}/>
    </main>
);

ReactDOM.render((
    <Router history={browserHistory}>
        <App />
    </Router>
), document.getElementById('root'));