<%-- 
    Document   : Borang_M
    Created on : Jul 21, 2011, 1:14:55 PM
    Author     : Rajesh
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){


        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
    });
    function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.BorangMActionBean_1">
    <s:messages/>
    <s:errors/>
    <c:if test="${showView}">
        <div  id="hakmilik_details">
            <fieldset class="aras1">
                <legend >
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/borangM" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.BorangMActionBean_1"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="display" value="true"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                            </s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </p>
                <br/><br/><br/>
                <c:if test="${actionBean.hakmilik ne null}">
                    <br/><br/>
                    <table>
                        <tr>
                            <td width="30%"><b>Saya <font style="text-transform: uppercase">${actionBean.pguna.nama}</font> Pentadbir Tanah bagi Dearah ${actionBean.hakmilik.daerah.nama} Dalam Negeri MELAKA pada menjalankan kuasa-kuasa yang diberi oleh subsekysen (2)
                                    seksyen 36 Akta Pengambilan Tanah 1960 dengan ini merujukan soalan yang berikut pada Mahkamah yang diputuskan:</b></td>
                        </tr>
                    </table>
                    <br/><br/>
                    <table >
                        <tr>
                            <td><b> <s:textarea disabled="true" name="ulasanPemohon" rows="5" cols="150" /></b></td>
                        </tr>
                    </table>
                    <br/><br/>
                    <table>
                        <tr>
                            <td width="30%"><b>Pihak-pihak yang berkepentingan,setakat yang saya tahu atau telah diberitahu,adalah seperti berikut:-</b></td>
                        </tr>
                    </table>
                    <p align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPihakList}"  cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/borangM" id="line1" >
                            <display:column property="pihak.nama" title="Nama"  sortProperty="true" />
                            <display:column title="Jenis Pihak Berkepentingan" style="vertical-align:baseline" property="jenis.nama"/>
                        </display:table>
                    </p>
                    <br/><br/>

                    <table>
                        <tr>
                            <td><b><s:textarea disabled="true" name="ulasanPenilai" rows="5" cols="150" /></b></td>
                        </tr>
                    </table>
                    <br/>
                    <p align="center">
                        <s:hidden name="idHakmilik" value="hakmilik.idHakmilik"/>
                        <s:button disabled="true" name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>

            </fieldset>
        </div>
    </c:if>
    <c:if test="${showEdit}">
        <div  id="hakmilik_details">
            <fieldset class="aras1">
                <legend >
                    <b>Maklumat Hakmilik Permohonan</b>
                </legend><br/>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/borangM" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:link beanclass="etanah.view.stripes.pengambilan.BorangMActionBean_1"
                                    event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="display" value="false"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                            </s:link>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    </display:table>
                </p>
                <br/><br/><br/>
                <c:if test="${actionBean.hakmilik ne null}">
                    <br/><br/>
                    <table>
                        <tr>
                            <td width="30%"><b>Saya <font style="text-transform: uppercase">${actionBean.pguna.nama}</font> Pentadbir Tanah bagi Dearah ${actionBean.hakmilik.daerah.nama} Dalam Negeri MELAKA pada menjalankan kuasa-kuasa yang diberi oleh subsekysen (2)
                                    seksyen 36 Akta Pengambilan Tanah 1960 dengan ini merujukan soalan yang berikut pada Mahkamah yang diputuskan:</b></td>
                        </tr>
                    </table>
                    <br/><br/>
                    <table >
                        <tr>
                            <td><b> <s:textarea name="ulasanPemohon" rows="5" cols="150" /></b></td>
                        </tr>
                    </table>
                    <br/><br/>
                    <table>
                        <tr>
                            <td width="30%"><b>Pihak-pihak yang berkepentingan,setakat yang saya tahu atau telah diberitahu,adalah seperti berikut:-</b></td>
                        </tr>
                    </table>
                    <p align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPihakList}"  cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/borangM" id="line1" >
                            <display:column property="pihak.nama" title="Nama"  sortProperty="true" />
                            <display:column title="Jenis Pihak Berkepentingan" style="vertical-align:baseline" property="jenis.nama"/>
                        </display:table>
                    </p>
                    <br/><br/>

                    <table>
                        <tr>
                            <td><b><s:textarea name="ulasanPenilai" rows="5" cols="150" /></b></td>
                        </tr>
                    </table>
                    <br/>
                    <p align="center">
                        <s:hidden name="idHakmilik" value="hakmilik.idHakmilik"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>

            </fieldset>
        </div>
    </c:if>

</s:form>

