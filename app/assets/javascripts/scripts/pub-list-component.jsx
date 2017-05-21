import React from 'react';
import axios from 'axios';

import PubListItemComponent from './pub-list-item-component.jsx';

class PubListComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            pubs: [],
            loggedIn: false
        };
    }

    render = () => {
        return <ul className="list-unstyled pub-list">
            {this.state.pubs.map(pub => {
                return <PubListItemComponent pub={pub} loggedIn={this.state.loggedIn} refresh={this.refreshPubs} />
            })}
        </ul>;
    };

    componentDidMount = () => {
        this.refreshPubs();
    };

    refreshPubs = () => {
        axios.get('/pubs').then(response => {
            const json = response.data;
            this.setState({
                pubs: json.pubs,
                loggedIn: !!json.maybeEmail

            }, () => console.log(this.state));
        });
    }
}

export default PubListComponent;