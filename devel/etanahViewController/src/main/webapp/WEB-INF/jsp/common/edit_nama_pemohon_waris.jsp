<%-- 
    Document   : edit_nama_pemohon
    Created on : Dec 21, 2009, 7:03:37 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        $('.edit').attr('disabled', 'disabled');
    });


    function save(event, f) {

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top: ($(window).height() - 50) / 2 + 'px',
                left: ($(window).width() - 50) / 2 + 'px',
                width: '50px'
            }
        });

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                    $.unblockUI();
                    self.close();
                }, 'html');
    }

    function doEdit(id) {
        if ($('#cb_' + id).is(':checked')) {
//            $('.view_' + id).hide();
//            $('.edit_' + id).show();
            $('.edit_' + id).attr('disabled', '');
        } else {
//            $('.view_' + id).show();
//            $('.edit_' + id).hide();
            $('.edit_' + id).attr('disabled', 'disabled');
        }
    }

</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.daftar.PihakKepentinganAction">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Waris</legend>   
            <c:if test="${!empty actionBean.hakmilikPihak.senaraiWaris}">
                <div class="content" align="center">
                    <table class="tablecloth" cellpadding="0" cellspacing="0" style="width:100%">
                        <thead>
                            <tr>
                                <th>Pilih</th>                                
                                <th>Nama Lama</th>
                                <th>Nama Baru</th>
                                <th>Jenis Pengenalan Lama</th>
                                <th>Jenis Pengenalan Baru</th>
                                <th>No Pengenalan Lama</th>
                                <th>No Pengenalan Baru</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${actionBean.hakmilikPihak.senaraiWaris}" varStatus="loop" var="item">
                                <tr>

                                    <td>                                        
                                        <s:checkbox name="cb" id="cb_${item.idWaris}" 
                                                    value="${item.idWaris}" 
                                                    onclick="doEdit('${item.idWaris}');"/>
                                    </td>                                    
                                    <td>                                        
                                        <s:hidden name="name_waris_lama_${item.idWaris}" value="${item.nama}"/>
                                        <span class="view_${item.idWaris} view">${item.nama}</span>                                        
                                    </td>
                                    <td>
                                        <span class="edit_${item.idWaris} edit">
                                            <s:text name="name_waris_${item.idWaris}" value="${actionBean.names[loop.count-1]}" class="edit_${item.idWaris}" disabled="true"/>
                                        </span>
                                    </td>
                                    <td>
                                        <s:hidden name="jeniskp_waris_lama_${item.idWaris}" value="${item.jenisPengenalan.kod}"/>
                                        <span class="view_${item.idWaris} view">${item.jenisPengenalan.nama}</span>                                        
                                    </td>
                                    <td>
                                        <span class="edit_${item.idWaris} edit">
                                            <s:select name="jeniskp_waris_${item.idWaris}" value="${actionBean.kod[loop.count-1]}" disabled="true" class="edit_${item.idWaris}">
                                                <s:option value="">Sila Pilih</s:option>
                                                <s:options-collection 
                                                    collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                            </s:select>
                                        </span>
                                    </td>
                                    <td>
                                        <s:hidden name="no_pengenalan_waris_lama_${item.idWaris}" value="${item.noPengenalan}"/>
                                        <span class="view_${item.idWaris} view">${item.noPengenalan}</span>                                        
                                    </td>
                                    <td>
                                        <span class="edit_${item.idWaris} edit">
                                            <s:text name="no_pengenalan_waris_${item.idWaris}" value="${actionBean.no[loop.count-1]}" disabled="true" class="edit_${item.idWaris}"/>
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="saveTukarWaris" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>                    
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br/>
            </c:if>
        </fieldset >
    </div>

</s:form>