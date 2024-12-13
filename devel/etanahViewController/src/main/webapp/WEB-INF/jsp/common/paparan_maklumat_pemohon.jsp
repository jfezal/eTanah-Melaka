<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.MaklumatPemohonActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pemohon
            </legend>
            <div class="content" align="center">
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

            </div>
        </fieldset>
    </div>
</s:form>

