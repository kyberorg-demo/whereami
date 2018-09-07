package ee.yals.whoami;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import ee.yals.whoami.res.R;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Theme(value = Lumo.class, variant = Lumo.DARK)
@Route("")
@SpringComponent
public class MainUI extends VerticalLayout {

    private Label hostnameLine;
    private Label ipAddrLine;

    public MainUI() {
        action();
        buildLayout();
    }

    private void action() {
        String hostname;
        String ipAddr;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            ipAddr = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = R.Text.HOST_WITH_UNKNOWN_ADDR;
            ipAddr = R.Text.UNKNOWN_IP;
        }


        hostnameLine = new Label(R.Text.I_AM + R.SPACE + hostname);
        ipAddrLine = new Label(R.Text.MY_IP + R.SPACE + ipAddr);
    }

    private void buildLayout() {
        hostnameLine.setId(R.Id.HOSTNAME);
        ipAddrLine.setId(R.Id.IP_ADDR);


        this.add(hostnameLine, ipAddrLine);
    }
}
