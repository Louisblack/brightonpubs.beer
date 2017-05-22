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
        return <div className="pub-list">
            {this.message()}
            <ul className="list-unstyled pub-list__list">
                {this.state.pubs.map(pub => {
                    return <PubListItemComponent pub={pub} loggedIn={this.state.loggedIn} refresh={this.refreshPubs} />
                })}
            </ul>
        </div>;
    };

    message = () => {
        if (this.state.loggedIn) {
            return <p>
                You've visited <span className="reet-big-text">{this.state.pubStats.visited}</span>
                <span> of </span><span className="reet-big-text">{this.state.pubStats.total}</span> pubs in Brighton.
                That's <span className="reet-big-text">{this.state.pubStats.percentage}%!</span>
            </p>
        } else {
            return <p>
                <a href="/login" className="reet-big-text">Log in</a> or <a href="signup" className="reet-big-text">sign up</a> to track the pubs you've visited.
            </p>
        }
    };

    componentDidMount = () => {
        this.refreshPubs();
    };

    refreshPubs = () => {
        axios.get('/pubs').then(response => {
            const json = response.data;
            this.setState({
                pubs: json.pubs,
                pubStats: json.pubStats,
                loggedIn: !!json.maybeEmail

            }, () => console.log(this.state));
        });
    }
}

export default PubListComponent;