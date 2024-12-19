
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function cari(f,event){        
        var q = $(f).formSerialize();       
        var url = f.action + '?' + event;
        $.ajax({
            url:url,
            type:'POST',
            data:q,
            error:function(xhr, ajaxOptions, thrownError) {
                alert("Tiada Hakmilik Dijumpai.Sila pastikan data - data hakmilik adalah tepat");
            },
            success:function(data){
                if(data == 0){
                    alert("Tiada Hakmilik Dijumpai.Sila pastikan data - data hakmilik adalah tepat");
                }else{
                    copyToTextBox(data);
                }
                
            }
        });
    }

    function validate_carian_hakmilik(){
    
        var a = $('#_kodDaerah').val();
        var b = $('#_kodBPM').val();
        
        var c = $('#_kodLot').val();
        var d = $('#_noLot').val();       
        
        var g = $('#_kodSeksyen').val();        
        var kodHakmilik = $('#_kodHakmilik').val();
        var noHakmilik = $('#_noHakmilik').val();
        
        var f1 = true;
        var f2 = true;
        
        if (a == '' || b == '') {
            alert ('Sila pilih daerah dan bandar pekan mukim !');
            return false;
        }

        if (c == '' || d == ''){
            f1 = false;
        }
        if (kodHakmilik == '' || noHakmilik == ''){
            f2 = false;
        }

        if (!f1 && !f2) {
            alert('Sila isi maklumat dengan tepat.');
            return false;
        }

        //        if(a == '') {
        //            alert('Sila Pilih Daerah.');
        //            $('#_kodDaerah').focus();
        //            return false;
        //        } else if(b == '') {
        //            alert('Sila Pilih Bandar Pekan Mukim.');
        //            $('#_kodBPM').focus();
        //            return false;
        //        } else if(c == '') {
        //            alert('Sila Pilih Jenis Lot.');
        //            $('#_kodLot').focus();
        //            return false;
        //        } else if(d == '') {
        //            alert('Sila Isi No Lot.');
        //            $('#_noLot').focus();
        //            return false;
        //        }
        return true;
    }

    function doSomething(id, val) {

        var kodDaerah = $('#_kodDaerah').val();
        var kodBPM = $('#_kodBPM').val();

        var url = '${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent&daerahPilihan='+ kodDaerah + '&type=' + val + '&bpm=' + kodBPM;
        $.ajax({
            url:url,
            success:function(data){                
                $dialog.html(data);
            },
            error:function(data){
                $dialog.html('Terdapat masalah teknikal pada sistem.');
            }
        });
    }

    function doReload() {
        var url = '${pageContext.request.contextPath}/hakmilik?reload';
        $.ajax({
            url:url,
            success:function(data){
                $dialog.html(data);
            },
            error:function(data){
                $dialog.html('Terdapat masalah teknikal pada sistem.');
            }
        });
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.HakmilikActionBean">


    <table>
        <tr>
            <td><font color="red">*</font>Daerah </td>
            <td>:</td>
            <td><%--<s:text name="hakmilik.daerah.kod" size="4" id="kodDaerah" readonly="true" /> ---%>
                <s:select name="hakmilik.daerah.kod" id="_kodDaerah" onchange="doSomething(this.id,'1');">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-map map="${actionBean.mapDaerah}"/>
                </s:select></td>
        </tr>
        <tr>
            <td><font color="red">*</font>Bandar Pekan Mukim</td>
            <td>:</td>
            <td>
                <%--<s:text name="kodBPM" size="4" id="kodBPM"/> ---%>
                <s:select name="hakmilik.bandarPekanMukim.kod" id="_kodBPM" onchange="doSomething('_kodDaerah','1');">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <%--<s:options-collection collection="${actionBean.senaraiBandarPekanMukim}" label="nama" value="kod" />--%>
                    <c:forEach items="${actionBean.senaraiBandarPekanMukim}" var="item">
                        <s:option value="${item.kod}">${item.bandarPekanMukim} - ${item.nama}</s:option>
                    </c:forEach>
                </s:select>
            </td>
        </tr>
        <tr>
            <td>Seksyen </td>
            <td>:</td>
            <td>
                <s:select name="hakmilik.seksyen.kod" id="_kodSeksyen">
                    <s:option value="">Sila Pilih</s:option>
                    <%--<s:options-collection collection="${actionBean.senaraiSeksyen}" label="nama" value="kod" />--%>
                    <c:forEach items="${actionBean.senaraiSeksyen}" var="item">
                        <s:option value="${item.kod}">${item.seksyen} - ${item.nama}</s:option>
                    </c:forEach>
                </s:select>
            </td>
        </tr>   
        <tr>
            <td colspan="3">
                <br/>
                <hr/>
                <br/>
            </td>
        </tr>

        <tr>
            <td>Lot </td>
            <td>:</td>
            <td>
                <s:select name="hakmilik.lot.kod" id="_kodLot">
                    <s:option value="">Sila Pilih</s:option>                    
                    <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod" />
                </s:select> /
                <s:text name="hakmilik.noLot" id="_noLot"/>
            </td>
        </tr>        

        <tr>
            <td colspan="3" align="center"><font color="red" size="4">atau</font> </td>
        </tr>             
        <tr>
            <td>Jenis Hakmilik </td>
            <td>:</td>
            <td>
                <s:select name="hakmilik.kodHakmilik.kod" id="_kodHakmilik">
                    <s:option value="">Sila Pilih</s:option>
                    <%--<s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod" />--%>
                    <c:forEach items="${list.senaraiKodHakmilik}" var="item">
                        <s:option value="${item.kod}">${item.kod} - ${item.nama}</s:option>
                    </c:forEach>
                </s:select>
            </td>
        </tr>
        <tr>
            <td>No Hakmilik </td>
            <td>:</td>
            <td><s:text name="hakmilik.noHakmilik" id="_noHakmilik"/></td>
        </tr>
    </table>
    <br/>
    <div align="right">   
        <s:button value="Cari" class="btn" name="carianHakmilikPopup" onclick="if(validate_carian_hakmilik())cari(this.form, this.name);"/>&nbsp;
        <s:button name="cl" value="Tutup" class="btn" onclick="closeDialog();"/>            
    </div>

</s:form>
