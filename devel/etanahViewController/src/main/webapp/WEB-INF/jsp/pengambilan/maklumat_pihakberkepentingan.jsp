<%-- 
    Document   : maklumat_pihakberkepentingan
    Created on : 22-Mar-2010, 09:36:06
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pengambilan.pihakberkepentinganPengambilanActionBean">
    <%--<s:hidden name="permohonanRujukanLuar.idRujukan"/>--%>
    <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Pihak Berkepentingan
        </legend>

        <p>
            <label for="Maklumat Pengambilan">Bilangan Pihak Berkepentingan :</label>
         
        </p>
        <p>
                <label for="Permohonan">Senarai Tuan Tanah :</label>
                4&nbsp;
            </p>
        <p>
                    <display:table class="tablecloth" style="width:90%;" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="hakmilik${line_rowNum}" style="text-decoration:underline;cursor:hand;" href="/etanah/common/maklumat_hakmilik_permohonan?showForm2&tab=true" paramId="idHakmilik" paramProperty="hakmilik.idHakmilik"  />
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="hakmilik${line_rowNum}" style="text-decoration:underline;cursor:hand;"/>
                        <display:column title="ID Hakmilik"><a href="#" onclick="p('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column property="hakmilik.luas" title="Luas" />
                        <display:column property="hakmilik.cawangan.name" title="Daerah" />
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
<%--
                        <display:table name="${actionBean.listPemohon}" id="line1" class="tablecloth" >
                           <display:column title="No">${line1_rowNum}</display:column>
                               <display:column property="pihak.nama" title="Nama"/>
                               <display:column property="pihak.noPengenalan" title="Nombor Pengenalan"/>
                               <display:column property="pihak.rumahAlamat1"  title="Alamat 1" />
                               <display:column property="pihak.rumahAlamat2" title="Alamat 2" />
                               <display:column property="pihak.rumahAlamat3" title="Alamat 3" />
                               <display:column property="pihak.rumahAlamat4" title="Alamat 4" />
                               <display:column property="pihak.rumahPoskod" title="Poskod" />
                               <display:column property="pihak.rumahNegeri.kod" title="Negeri" />
                        </display:table>
        </p>--%>
        
    </fieldset>
</div>


    </s:form>
