<%-- 
    Document   : kemasukan_pihak_berkepentingan
    Created on : 15-Oct-2009, 03:49:43
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle">
    <form:form beanclass="etanah.view.stripes.PihakBerkepentinganActionBean">
        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>

            <p>
                <label for="nama">Nama :</label>
                <s:text name="pihak.nama"/>
            </p>
            <p>
                <label for="nama">Jenis Pihak Berkepentingan :</label>
                <s:select name="">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPihakBerkepentingan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label for="Jenis Pengenalan">Jenis Pengenalan :</label>
                <s:select name="">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>                
            </p>
            <p>
                <label for="No Pengenalan">No Pengenalan :</label>
                <s:text name="pihak.noPengenalan"/>
            </p>

            <p>
                <label for="alamat">Alamat Berdaftar :</label>
                <s:text name="pihak.rumahAlamat1"/>
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="pihak.rumahAlamat2"/>
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="pihak.rumahAlamat3"/>
            </p>
            <p>
                <label for="alamat">Alamat Surat-Menyurat:</label>
                <s:text name="pihak.suratAlamat1"/>
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="pihak.suratAlamat2"/>
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="pihak.suratAlamat3"/>
            </p>
            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="pihak.suratNegeri">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>     
            </p>
            <p>
                <label for="Bandar">Bandar :</label>
                <s:select name="">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label for="poskod">Poskod :</label>
                <s:text name="pihak.rumahPoskod"/>
            </p>
            <p>
                <label>Taraf Kaum :</label>
                <s:text name="pihak.bangsa"/>
            </p>
            <p>
                <label>Jenis PB :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>No Kumpulan PB :</label>
                <s:text name=""/>
            </p>
            <p>
                <label>Kepentingan Negeri / Persekutuan :</label>
                <s:select name="">
                    <s:option></s:option>
                </s:select>
            </p>
            <p>
                <label>Warganegara :</label>
                <s:select name="">
                    <s:option></s:option>
                </s:select>
            </p>
            <p>
                <label>Syer :</label>
                <s:text name="" /> /
                <s:text name="" />
            </p>
            <p>
                <label>Jumlah Syer :</label>
                <s:text name="" /> /
                <s:text name="" />
            </p>
            <p>
                <label>No Telefon :</label>
                <s:text name="" />
            </p>
            <p>
                <label>No Telefon Pejabat :</label>
                <s:text name="" /> Samb. <s:text name="" />
            </p>
            <p>
                <label>Emel :</label>
                <s:text name="" />
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="popup" id="popup" value="Tambah" class="btn"/>
            </p>
        </fieldset>
    </form:form>
</div>
