<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page contentType="text/html;charset=windows-1252" language="java"%>



<style type="text/css">
    input.error { background-color: yellow; }
</style>


<form:form beanclass="etanah.view.stripes.GadaianActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik yang Terlibat</legend>
            <div class="content" align="center">
                <p>
                    <label for="idHakmilik">ID Hakmilik :</label>
                </p>
            </div>
        </fieldset>
        <fieldset class="aras1">
            <legend>Maklumat Urusniaga</legend>
            <div class="content" align="center">
                <p>
                    <label for="amaun">Amaun/Pinjaman (RM) :</label>
                    <s:text name="" />
                </p>
                <p>
                    <label for="duti">Duti Setem (RM) :</label>
                    <s:text name="" />
                </p>
                <p>
                    <label for="fail">No. Fail :</label>
                    <s:text name="" />
                </p>
            </div>
        </fieldset>

        <fieldset class="aras1">
            <legend>Senarai Pihak Berkepentingan</legend>
            <p>
                <label for=""><display:table class="tablecloth" name="${actionBean.listPihakBerkepentingan}" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column property="idPermohonan" title="ID Mohon" />
                <display:column property="penyerahNama" title="Nama Penyerah" />
                <display:column property="penyerahNoRujukan" title="Penyerah No Rujukan"/>
            </display:table></label>
            </p>            
        </fieldset>

        <fieldset>
            <legend>Maklumat Pihak Berkepentingan Baru</legend>
            <table border="0" width="100%">
                <tr>
                    <td width="25%" align="right">Nama</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>
                </tr>

                <tr>
                    <td width="25%" align="right">Jenis Pihak Berkepentingan</td>
                    <td width="1%" align="center">:</td>
                    <td>
                <stripes:select name="">
                    <stripes:option value="pemajak">pemajak</stripes:option>
                    <stripes:option value="penyewa">penyewa</stripes:option>
                    <stripes:option value="pemegang gadaian">pemegang gadaian</stripes:option>
                </stripes:select>
                </td>
                </tr>

                <tr>
                    <td width="25%" align="right">Jenis Pengenalan</td>
                    <td width="1%" align="center">:</td>
                    <td>
                <stripes:select name="">
                    <stripes:option value="IC Baru">IC Baru</stripes:option>
                    <stripes:option value="IC Lama">No Syarikat</stripes:option>
                    <stripes:option value="Passport">Passport</stripes:option>
                    <stripes:option value="Lain-Lain">Lain-Lain</stripes:option>
                </stripes:select>
                </td>
                </tr>

                <tr>
                    <td width="25%" align="right">Nombor Pengenalan</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>
                </tr>

                <tr>
                    <td width="25%" align="right">Alamat</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right"></td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right"></td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>

                <tr>
                    <td width="25%" align="right">Negeri</td>
                    <td width="1%" align="center">:</td>
                    <td>
                <stripes:select name="">
                    <stripes:option value=" ">database</stripes:option>
                    <stripes:option value=" ">database</stripes:option>
                </stripes:select>
                </td>
                </tr>

                <tr>
                    <td width="25%" align="right">Bandar</td>
                    <td width="1%" align="center">:</td>
                    <td>
                <stripes:select name="">
                    <stripes:option value=" ">database</stripes:option>
                    <stripes:option value=" ">database</stripes:option>
                </stripes:select>
                </td>
                </tr>

                <tr>
                    <td width="25%" align="right">Poskod</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:text name=""/></td>   
                </tr>  
            </table>
        </fieldset>



        <%--<fieldset class="aras1">
            <legend>Syor Cadangan Pembantu Tadbir</legend>
            <table border="0" width="100%">
                <tr>
                    <td width="25%" align="right">Syor Cadangan </td>
                    <td width="1%" align="right">:</td>
                    <td align="right" width="5%"><stripes:radio name="a1" value="Lulus"/>Daftar</td>
                <td align="center"><stripes:radio name="a1" value="Tolak"/>Tolak</td>
                <td align="left"><stripes:radio name="a1" value="Gantung"/>Gantung</td>
                </tr>
            </table>
            <table border="0" width="100%">
                <tr>
                    <td width="25%" align="right">Ulasan</td>
                    <td width="1%" align="center">:</td>
                    <td><stripes:textarea name="" cols="70" rows="5"/></td>
                </tr>
            </table>
        </fieldset>--%>

        <fieldset class="aras1">
            <table class="rowlabel1">
                <tr>
                    <td>
                <stripes:submit name="simpan" value="Simpan"/>
                <stripes:submit name="sah" value="Sah"/>
                <stripes:submit name="hantar" value="Hantar"/>
                <stripes:submit name="keluar" value="Keluar"/>
                </td>
                </tr>
            </table>
        </fieldset>
    </div>
</form:form>
