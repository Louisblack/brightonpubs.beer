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
            return this.getStats()
        } else {
            return this.loginMessage()
        }
    };

    loginMessage = () => {
        return <div>
            <h3>Brighton has hundreds of great pubs</h3>
            <p>
                This site gives you the a way to find undiscovered gems that they'd never usually find.
            </p>
            <p>
                <a href="/login" className="reet-big-text"> Log in</a> or <a href="signup" className="reet-big-text">sign
                up</a> to start tracking the pubs you've visited and find out which ones you haven't.
            </p>
            <p>
                Over time we'll add features to make discovering new pubs even easier. If you have any suggestions
                for new features or want to let us know about a new pub or one that is no longer open then contact
                us over on Twitter - <a href="https://twitter.com/louisblack">@louisblack</a>.
            </p>
        </div>;
    };

    getStats = () => {
        return <div>
            <p>
                You've visited <span className="reet-big-text">{this.state.pubStats.visited}</span>
                <span> of </span><span className="reet-big-text">{this.state.pubStats.total}</span> pubs in Brighton.
                That's <span className="reet-big-text">{this.state.pubStats.percentage}%!</span>
            </p>
            {this.getInstructions()}
        </div>;
    };

    getInstructions = () => {
        if (this.state.pubStats.visited === 0) {
            return <p>Hit the black tick next to the pubs you visited.</p>
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