package Client;

import Client.exceptions.InvalidInputException;
import Client.exceptions.NoConnectionException;
import Client.utillity.Color;
import Client.utillity.InputManager;
import Client.utillity.OutputManager;
import Common.exceptions.IllegalAddressException;
import Common.utills.Checker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;



public final class Client {
    private static final int TIMEOUT = 100;
    private static final int BUFFER_SIZE = 3048;
    private static final int RECONNECTION_ATTEMPTS = 5;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        OutputManager outputManager = new OutputManager(System.out);
        InputManager inputManager = new InputManager(System.in, outputManager);
        //SpaceMarineReader spaceMarineReader = new SpaceMarineReader(inputManager);
        //SpaceMarineFiller spaceMarineFiller = new SpaceMarineFiller(spaceMarineReader, inputManager, outputManager);
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try (DatagramChannel client = DatagramChannel.open()) {
                InetSocketAddress serverAddress = Checker.checkAddress(args[0], args[1]);
                client.bind(null).configureBlocking(false);
                //Requester requester = new Requester(client, serverAddress, TIMEOUT, BUFFER_SIZE, RECONNECTION_ATTEMPTS, outputManager);
                //HashMap<String, CommandRequirement> requirements = requester.sendPullingRequest();
                //ConsoleManager consoleManager = new ConsoleManager(requirements, inputManager, outputManager, spaceMarineFiller, requester);
                //consoleManager.start();
            } catch (IllegalAddressException e) {
                outputManager.printlnImportantColorMessage(e.getMessage(), Color.RED);
            } catch (IOException e) {
                outputManager.printlnImportantColorMessage("error during connection:", Color.RED);
                e.printStackTrace();
            }
        } else {
            outputManager.printlnImportantColorMessage("please enter a server hostname and port as a command "
                    + "line arguments", Color.RED);
        }
    }
}