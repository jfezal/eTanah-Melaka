<%-- 
    Document   : view_kehadiran_NEW
    Created on : Jul 5, 2012, 11:59:40 AM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="etanah.model.Pengguna"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
</script>

<s:form beanclass="etanah.view.stripes.lelong.MaklumatKehadiranNEWActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <br>

    <div class="subtitle" align="center">
        <fieldset class="aras1">

            <legend>
                Penggadai/Pihak Berkepentingan
            </legend>
            <br><br>
            <display:table class="tablecloth" name="${actionBean.listHakmilikPihakBerkepentingan}"  cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_kehadiran_NEW" id="line">
                <display:column title="Bil" class="rowLine" sortable="true" >${line_rowNum}</display:column>
                <display:column title="Nama" property="pihak.nama" style="text-transform:uppercase;"/>
                <display:column title="IDHakmilik" property="hakmilik.idHakmilik"/>
                <display:column title="No.Pengenalan" property="pihak.noPengenalan" class="${line_rowNum}" style="text-transform:uppercase;"/>
                <display:column title="Status" property="jenis.nama" class="${line_rowNum}" style="text-transform:uppercase;"/>
            </display:table><br>
        </fieldset>
        <fieldset class="aras1">

            <legend>
                Senarai Kehadiran
            </legend><br><br>

            <display:table class="tablecloth" name="${actionBean.listKehadiran}"  cellpadding="0" cellspacing="0" requestURI="/lelong/maklumat_kehadiran_NEW" id="line">
                <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                <display:column title="Nama" class="nama" style="text-transform:uppercase;">
                    <c:if test="${line.idPenyerah eq null}">
                        ${line.hakmilikPihakBerkepentingan.pihak.nama}
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        ${actionBean.peguam.nama}
                    </c:if>
                </display:column>
                <display:column title="Status" class="${line_rowNum}" style="text-transform:uppercase;">
                    <c:if test="${line.idPenyerah eq null}">
                        <c:if test="${line.permohonanPihak ne  null}">
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod ne 'PG'}">
                                Penggadai
                            </c:if>
                            <c:if test="${line.hakmilikPihakBerkepentingan.jenis.kod eq 'PG'}">
                                ${line.hakmilikPihakBerkepentingan.jenis.nama}
                            </c:if>
                        </c:if>
                        <c:if test="${line.permohonanPihak eq null}">
                            ${line.hakmilikPihakBerkepentingan.jenis.nama}
                        </c:if>
                    </c:if>
                    <c:if test="${line.idPenyerah ne null}">
                        Peguam
                    </c:if>
                </display:column>
                        
                <display:column title="Kehadiran" class="${line_rowNum}">
                    ${line.hadir eq 'Y' ? "YA" : "TIDAK" }
                </display:column>

                <display:column title="Wakil/Hadir Bersama">
                     <c:if test="${line.wakilNama eq null}">
                        -
                    </c:if>
                    ${line.wakilNama}
                </display:column>

                <display:column title="Catatan" class="${line_rowNum}" style="text-transform:uppercase;">
                    <c:if test="${line.keterangan eq null}">
                        -
                    </c:if>
                    ${line.keterangan}
               </display:column>

            </display:table><br>
        </fieldset>

    </div>
</s:form>

