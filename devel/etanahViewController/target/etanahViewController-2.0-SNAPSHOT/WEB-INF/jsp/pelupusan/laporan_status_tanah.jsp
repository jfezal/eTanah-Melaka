<%-- 
    Document   : laporan_status_tanah
    Created on : Feb 2, 2010, 1:48:16 PM
    Author     : muhammad.amin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>


<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganJktdActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Status Tanah
            </legend>
            <p>
                <label>Tanah 
                    <s:select name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod" style="width:150px;">
                        <s:option>--Sila Pilih--</s:option>
                        <s:option>Kosong Kerajaan</s:option>
                        <s:option>Milik</s:option>
                        <s:option>Rizab</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                    </s:select>:
                </label>
                <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" size="45" />
            </p>
            <p>
                <label>Tanah <s:select name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod" style="width:150px;">
                        <s:option>--Sila Pilih--</s:option>
                        <s:option>Desa</s:option>
                        <s:option>Pekan</s:option>
                        <s:option>Bandar</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                    </s:select>:
                </label>

                <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" size="45"/>
            </p>

            <p>
                <label>Tanah simpanan Melayu :
                </label>
                <s:select  name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod" style="width:150px;">
                    <s:option>--Sila Pilih--</s:option>
                    <s:option>Dalam</s:option>
                    <s:option>Luar</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                </s:select>
                <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" size="45"/>
            </p>

            <p>
                <label>Rizab Hutan Kekal :
                </label>
                <s:select  name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod" style="width:150px;">
                    <s:option>--Sila Pilih--</s:option>
                    <s:option>Dalam</s:option>
                    <s:option>Luar</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                </s:select>
                <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" size="45"/>
            </p>

            <p>
                <label>Tanah GSA :
                </label>
                <s:select  name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod" style="width:150px;">
                    <s:option>--Sila Pilih--</s:option>
                    <s:option>Dalam</s:option>
                    <s:option>Luar</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                </s:select>
                <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" size="45"/>
            </p>

            <p>
                <label>Permohonan Terdahulu :
                </label>
                <s:select  name="permohonan.senaraiHakmilik[0].hakmilik.kodUnitLuas.kod" style="width:150px;">
                    <s:option>--Sila Pilih--</s:option>
                    <s:option>Ada</s:option>
                    <s:option>Tiada</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                </s:select>
                <s:text name="permohonan.senaraiHakmilik[0].hakmilik.luas" size="45"/>
            </p>

            <p>
                <label>Lain-lain Hal :</label>
                <s:textarea name="noMahkamah" cols="50"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPindahMilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
            <br>
        </fieldset>

    </div>
</s:form>
