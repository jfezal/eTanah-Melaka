<%-- 
  
    Author     : afham
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pelupusan.Penyediaan_PembaharuanLPS_pejabatTanah_ActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="center">Penyediaan Pembaharuan Lesen</legend>
        <fieldset class="aras2">
            <legend>Maklumat Bayaran</legend>
            <div align="center">
            <display:table name="" id="line1" class="tablecloth" requestURI="">

                   <display:column property="" title="No.Lesen" />
                    <display:column property="" title="Bayaran(RM)" />
                    <display:column property="" title="Tempoh Berakhir" />

                </display:table>
            </div>
        </fieldset>
        <fieldset class="aras2">
            <legend>Maklumat Pemohon</legend>
           <p>
                <label>Nama :</label>
              <%--  ${actionBean}--%>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
             <%--   ${actionBean}--%>
            </p>
            <p>
                <label>Nombor Kad Pengenalan :</label>
              <%--  ${actionBean}--%>
            </p>
            <p>
                <label>Alamat :</label>
               <%-- ${actionBean}--%>
            </p>
            <p>
                <label>Poskod :</label>
              <%--  ${actionBean}--%>
            </p>
            <p>
                <label>Bandar :</label>
               <%-- ${actionBean}--%>
            </p>
            <p>
                <label>Negeri :</label>
            <%--    ${actionBean}--%>
            </p>
            <p>
                <label>Tarikh Dikeluarkan :</label>
               <%-- ${actionBean}--%>
            </p>
        </fieldset>
        <fieldset class="aras2">
           
            <legend>Perihal Tanah</legend>
             <div align="center">
            <display:table name="${actionBean}" id="line1" class="tablecloth" requestURI="">

                    <display:column property="" title="Bandar/Pekan/Mukim" />
                    <display:column property="" title="Penggunaan Tanah" />
                    <display:column property="" title="No.Lot/Plot" />
                    <display:column property="" title="Luas Tanah" />
                    <display:column property="" title="Peruntukan Tambah" />
              </display:table>
            </div>
             
        </fieldset>
         <center><s:button name="simpanPembaharuanLesen" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></center>
         </fieldset>
       
    </div>

</s:form>
