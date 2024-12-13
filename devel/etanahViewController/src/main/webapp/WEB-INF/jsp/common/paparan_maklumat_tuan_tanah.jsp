<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.common.MaklumatTuanTanahActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tuan Tanah
            </legend>
            <div class="content" align="center">

                <display:table name="${actionBean.listTuanTanah}" id="line" class="tablecloth" >
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="nama" title="Nama"/>
                    <display:column property="noPengenalan" title="Nombor Pengenalan"/>
                    <display:column property="rumahAlamat1"  title="Alamat 1" />
                    <display:column property="rumahAlamat2" title="Alamat 2" />
                    <display:column property="rumahAlamat3" title="Alamat 3" />
                    <display:column property="rumahAlamat4" title="Alamat 4" />
                    <display:column property="rumahPoskod" title="Poskod" />
                    <display:column property="rumahNegeri.nama" title="Negeri" />
                </display:table>

            </div>
        </fieldset>
    </div>
</s:form>
