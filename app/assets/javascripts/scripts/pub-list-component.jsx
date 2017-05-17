import React from 'react';
import axios from 'axios';

class PubListComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            pubs: []
        };
    }

    render = () => {

        return <ul>
            {this.state.pubs.map(pub => {
                let button = !pub.visited ? <button onClick={e => this.visitPub(pub.id)}>Been!</button> : ""
                return <li key={pub.id}>
                    {pub.name} - {pub.visited ? "Visited" : "Not Visited"}
                    {button}
                </li>
            })}
        </ul>;
    };

    visitPub = (id) => {
        axios.post(`/pubs/visit/${id}`)
            .then(this.refreshPubs);
    };

    componentDidMount = () => {
        this.refreshPubs();
    };

    refreshPubs = () => {
        axios.get('/pubs').then(response => {
            const json = response.data;
            this.setState({
                pubs: json
            }, () => console.log(this.state));
        });
    }
}

export default PubListComponent;