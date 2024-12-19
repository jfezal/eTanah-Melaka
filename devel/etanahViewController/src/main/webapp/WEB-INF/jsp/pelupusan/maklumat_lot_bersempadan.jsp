<%-- 
    Document   : maklumat_lot_bersempadan
    Created on : Jan 7, 2010, 11:57:47 AM
    Author     : zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.pelupusan.PelupusanActionBean">
    <p>LAPORAN TANAH</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Lot-lot Bersempadan
            </legend>
            <p>
                <label>Arah :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Daerah :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>No Lot/Plot/PT :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
                <s:text name=" "/>
                <s:submit name="cari" value="Cari" class="btn"/>

            </p>
            <p>
                <label>Status :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Jenis Kegunaan Tanah Semasa :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>
                <label>Guna Tanah Semasa :</label>
                <s:select name="">
                    <s:option value="">-- Sila Pilih --</s:option>
                </s:select>
            </p>
            <p>&nbsp;</p>


        </fieldset>
        <p>&nbsp;</p>
        <p>
            <label>&nbsp;</label>
            <s:submit name="tambah" value="Simpan & Tambah" class="btn"/>
            <s:submit name="simpan" value="Simpan" class="btn"/>
            <s:reset name="reset" value="Isi Semula" class="btn"/>
            <s:submit name="keluar" value="Keluar" class="btn"/>
        </p>
    </div>
</s:form>
