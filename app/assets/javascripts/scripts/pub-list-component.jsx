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
        return <div>
            {this.message()}
            <ul className="list-unstyled pub-list">
                {this.state.pubs.map(pub => {
                    return <PubListItemComponent pub={pub} loggedIn={this.state.loggedIn} refresh={this.refreshPubs} />
                })}
            </ul>
        </div>;
    };

    message = () => {
        if (this.state.loggedIn) {
            return <p>
                You have visited <span>{this.state.pubStats.visited}</span> of <span>{this.state.pubStats.total}</span>.
                That's <span>{this.state.pubStats.percentage}%</span>!
            </p>
        } else {
            return <p>
                <a href="/login">Log in</a> or <a href="signup">sign up</a> to track the pubs you've visited.
            </p>
        }
    }

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