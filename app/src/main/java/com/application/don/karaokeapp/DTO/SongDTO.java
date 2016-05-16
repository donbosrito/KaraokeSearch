package com.application.don.karaokeapp.DTO;

/**
 * Created by Don on 5/13/16.
 */
public class SongDTO {
    private String _code;
    private String _name;
    private String _lyric;
    private boolean _favorite;

    public SongDTO() {
    }

    public SongDTO(String _code, String _name, String _lyric, boolean _favorite) {
        this._code = _code;
        this._name = _name;
        this._lyric = _lyric;
        this._favorite = _favorite;
    }

    public String get_code() {
        return _code;
    }

    public void set_code(String _code) {
        this._code = _code;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_lyric() {
        return _lyric;
    }

    public void set_lyric(String _lyric) {
        this._lyric = _lyric;
    }

    public boolean is_favorite() {
        return _favorite;
    }

    public void set_favorite(boolean _favorite) {
        this._favorite = _favorite;
    }
}
