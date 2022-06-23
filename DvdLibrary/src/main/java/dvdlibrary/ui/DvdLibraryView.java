package dvdlibrary.ui;

import dvdlibrary.dto.DVD;

import java.util.List;

public class DvdLibraryView {
    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD to the collection");
        io.print("2. Remove a DVD from the collection");
        io.print("3. Edit information for an existing DVD in the collection");
        io.print("4. List DVD's");
        io.print("5. Search DVD by title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }

    public int printEditMenuAndGetSelection() {
        io.print("Edit DVD Menu");
        io.print("1. Edit title");
        io.print("2. Edit release date");
        io.print("3. Edit MPAA rating");
        io.print("4. Edit director's name");
        io.print("5. Edit user rating");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }

    public DVD getNewDvdInfo() {
        String title = io.readString("Please enter DVD tile");
        String releaseDate = io.readString("Please enter DVD release Date");
        String MPAA = io.readString("Please enter MPAA rating");
        String directorsName = io.readString("Please enter Directors full name");
        String studio = io.readString("Please enter Studio name");
        String userRating = io.readString("Please enter user rating");

        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAA(MPAA);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }

    public void displayCreateDVDBanner(){
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===");
        io.print(message);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");

    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
            io.print("DVD successfully removed.");
        }else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display DVD ===");
    }

    public void displayDvdList(List<DVD> dvdList) {
        for (DVD currentDvd : dvdList) {
            String DvdInfo = String.format("#%s : %s %s %s %s %s", currentDvd.getTitle(), currentDvd.getReleaseDate(),currentDvd.getMPAA(), currentDvd.getDirectorsName(), currentDvd.getStudio(),currentDvd.getUserRating());
            io.print(DvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");

    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMPAA());
            io.print(dvd.getDirectorsName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditDvdSuccess() {
        io.readString(
                "DVD successfully Edited.  Please hit enter to continue");
    }

    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    public void displayEditMpaaBanner() {
        io.print("=== Edit DVD MPAA rating ===");
    }

    public void displayEditDirectorNameBanner() {
        io.print("=== Edit DVD Director's Name ===");
    }

    public void displayEditStudio() {
        io.print("=== Edit DVD Studio ===");
    }

    public void displayEditUserRating() {
        io.print("=== Edit DVD User Rating ===");
    }

    public String getNewReleaseDate() {
        return io.readString("Please enter new release date.");
    }

    public String getNewMpaaRating() {
        return io.readString("Please enter new MPAA rating.");
    }

    public String getNewDirectorName() {
        return io.readString("Please enter new director's name.");
    }

    public String getNewUserRating() {
        return io.readString("Please enter new user rating.");
    }

    public String getNewStudio() {
        return io.readString("Please enter new studio.");
    }

    public void displayNullDVD() {
        io.print("No such DVD.");
        io.readString("Please hit enter to continue.");
    }
}
